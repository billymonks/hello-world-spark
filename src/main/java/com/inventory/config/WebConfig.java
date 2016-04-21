package com.inventory.config;

import com.inventory.autofilesystem.AutoFileSystem;
import com.inventory.car.Car;

import com.inventory.model.DirectorySearch;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import com.inventory.service.CarService;
import org.eclipse.jetty.util.UrlEncoded;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebConfig {
	
	private CarService service;
	private AutoFileSystem autoFileSystem;

	public WebConfig(CarService service) {
		this.service = service;
		autoFileSystem = new AutoFileSystem();
		staticFileLocation("/public");
		setupRoutes();
	}
	
	private void setupRoutes() {
		/*
		 * Shows a users timeline or if no user is logged in,
		 *  it will redirect to the public timeline.
		 *  This timeline shows the user's messages as well
		 *  as all the messages of followed users.
		 */

		get("/hello", (req, res) -> "Hello World");

		get("/", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Cars");
			List<Car> cars = service.getCars();
			map.put("cars", cars);
			return new ModelAndView(map, "cars.ftl");
		}, new FreeMarkerEngine());
		before("/", (req, res) -> {

		});

		get("/search", (req, res) -> {
			//Map<String, Object> map = new HashMap<>();
			//MultiMap<String> params = new MultiMap<~>();

			//Car c = new Car();

			//BeanUtils.populate(c, params);

			//List<Car> cars = service.getMatchingCars(c);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Files");
			map.put("directories", autoFileSystem.getDirectories());
			map.put("files", autoFileSystem.getFiles());

			return new ModelAndView(map, "autocomplete.ftl");
		}, new FreeMarkerEngine());

		/*
		 * Registers a new car.
		 */
		post("/submit", (req, res) -> {
			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8");
			Car c = new Car();
			//c.setMake("Honda");
			//c.setModel("Accord");
			//c.setPrice(1000);
			BeanUtils.populate(c, params);
			service.submitCar(c);
			res.redirect("/");
			return null;
        });
		/*
		 * Checks if the user is authenticated
		 */
		before("/submit", (req, res) -> {

		});

		/*post("/searchmake", (req, res) -> {
			Map<String, Object> map = new HashMap<>();

			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8");

			Car c = new Car();

			BeanUtils.populate(c, params);
			service.getMatchingCars(c);

			return new ModelAndView(map, "autocomplete.ftl");
		});*/

		post("/gotodir", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			MultiMap<String> params = new MultiMap<String>();
			UrlEncoded.decodeTo(req.body(), params, "UTF-8");


			DirectorySearch subdir = new DirectorySearch();
			BeanUtils.populate(subdir, params);

			autoFileSystem.PopulateFiles(subdir.getSubdir());
			res.redirect("/search");
			return null;
		});

		post("/gotoroot", (req, res) -> {
			autoFileSystem.PopulateFiles("");
			res.redirect("/search");
			return null;
			});


	}

	private String renderContent(String htmlFile) {
		try {
			// If you are using maven then your files
			// will be in a folder called resources.
			// getResource() gets that folder
			// and any files you specify.
			URL url = getClass().getResource(htmlFile);

			// Return a String which has all
			// the contents of the file.
			Path path = Paths.get(url.toURI());
			return new String(Files.readAllBytes(path), Charset.defaultCharset());
		} catch (IOException | URISyntaxException e) {
			// Add your own exception handlers here.
		}
		return null;
	}


}
