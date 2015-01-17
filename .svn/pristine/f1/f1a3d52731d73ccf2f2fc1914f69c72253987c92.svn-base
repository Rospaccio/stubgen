package org.merka.stubgen.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarInputStream;

import javax.servlet.http.HttpServletRequest;

import org.merka.stubgen.MockObjectGenerator;
import org.merka.stubgen.classloader.JarInputStreamClassLoader;
import org.merka.stubgen.exception.MockGenException;
import org.merka.stubgen.web.entity.StubClass;
import org.merka.stubgen.web.entity.UploadedJarFile;
import org.merka.stubgen.web.session.JarManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Controller
@SessionAttributes(value = {"stubClasses"})
public class UploadFormController
{
	private static transient Logger logger = LoggerFactory.getLogger(UploadFormController.class);
	
	public static final String JSESSIONIDKEY = "JSESSIONID";

	public static final String UPLOAD_FORM_VIEW = "uploadFormView";
	public static final String MESSAGE_VIEW = "showMessage";
	public static final String ERROR_VIEW = "uncaughtErrorView";
	
	private MultipartFile jarFile;
	
	@Autowired
	private JarManager jarManager;

	public JarManager getJarManager()
	{
		return jarManager;
	}

	public void setJarManager(JarManager jarManager)
	{
		this.jarManager = jarManager;
	}

	public MultipartFile getJarFile()
	{
		return jarFile;
	}

	public void setJarFile(MultipartFile jarFile)
	{
		this.jarFile = jarFile;
	}

	@RequestMapping(value = {"/uploadForm"})
	public ModelAndView enter(
			@ModelAttribute("uploadedFile")UploadedJarFile file, 
			@CookieValue(value = JSESSIONIDKEY) String jsessionid,
			BindingResult result)
	{
		Map<String, Object> model = new HashMap<>();
		model.put("jsessionid", jsessionid);
		return new ModelAndView(UPLOAD_FORM_VIEW, model);
	}
	
	@RequestMapping(value = "/submitFile")
	public ModelAndView submitFile(
			HttpServletRequest request,
			@ModelAttribute("uploadedFile") UploadedJarFile file,
			@CookieValue(value = JSESSIONIDKEY) String jsessionid,
			BindingResult result) throws IOException
	{
		Map<String, Object> model = new HashMap<>();
		model.put("jsessionid", jsessionid);
		String title = "Upload OK";
		// for each class, this array contains a messages with the corresponding report 
		ArrayList<StubClass> stubClasses = new ArrayList<>();
		
		model.put("title", title);
		
		JarInputStream jarStream = new JarInputStream(file.getUploadedFile().getInputStream());
		JarInputStreamClassLoader classLoader = new JarInputStreamClassLoader(jarStream);
		MockObjectGenerator generator = new MockObjectGenerator();

		for(String className : classLoader.getAvailableClasses())
		{
			logger.info("Class found --> " + className + ". ");
			Class<?> clazz = null;
			boolean loadable = true;
			try
			{
				clazz = classLoader.loadClass(className);
				logger.info(clazz.getName());
			}
			catch(Throwable t)
			{
				loadable = false;
				logger.error("Impossible to load the class", t);
			}
			
			boolean instantiable = true;
			try
			{
				Object obj = generator.generate(classLoader.loadClass(className));
			}
			catch(Throwable e)
			{
				instantiable = false;
				logger.error("Impossible to instantiate the class", e);
			}
			
			stubClasses.add(new StubClass(className, loadable, instantiable, clazz));
		}
		
		getJarManager().put(jsessionid, classLoader);
		
		model.put("stubClasses", stubClasses);
		return new ModelAndView(UPLOAD_FORM_VIEW, model);
	}
	
	protected void updateModel(String jsessionid)
	{
		
	}
	
	@RequestMapping("/instantiate")
	public String instantiate(
			HttpServletRequest request,
			@CookieValue (value = JSESSIONIDKEY) String jsessionid,
			@RequestParam (value = "className") String className,
			Model model) throws ClassNotFoundException, MockGenException, JsonProcessingException
	{
		logger.info(String.format("Trying to instantiate class %s", className));
		
		// This snippet is just to try another way to access the JarManager bean
		WebApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
		JarManager jarManagerTemp = (JarManager)context.getBean("jarManager");
		JarInputStreamClassLoader cached = jarManagerTemp.get(jsessionid);
		logger.info(cached.toString());
		// end snippet
		
		String json = null;
		if(cached != null)
		{
			Class<?> clazz = cached.loadClass(className);
			MockObjectGenerator generator = new MockObjectGenerator();
			Object instance = generator.generate(clazz);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			json = mapper.writeValueAsString(instance);
		}
		
		if(json != null)
		{
			model.addAttribute("plainText", json);
		}
		model.addAttribute("jsessionid", jsessionid);
		return UPLOAD_FORM_VIEW;
	}
	
	@ExceptionHandler
	public String onUncaughtError(Throwable t)
	{
		logger.error("Uncaught Error: ", t);
		return ERROR_VIEW;
	}
}
