package com.xworkz.spring.railway.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequestMapping("/")
public class RailwayController {

	private Map<String, String> map;

	public RailwayController() {
		System.out.println("Created " + this.getClass().getSimpleName());
	}

	@PostConstruct
	public void init() {
		System.out.println("Invoked initalize method in controller");
		map = new LinkedHashMap<String, String>();
		map.put("MSR", "SHEXP");
		map.put("GOA", "GOAN");
		map.put("DEL", "EXP");
	}

	@RequestMapping(value = "/book.do", method = RequestMethod.POST)
	public String onBook(@RequestParam String name, @RequestParam int age, @RequestParam String address,
			@RequestParam String source, @RequestParam String destination, @RequestParam String date, Model model) {
		System.out.println("Invoked onBook");

		System.out.println("Name : " + name);
		System.out.println("Age : " + age);
		System.out.println("Address : " + address);
		System.out.println("Sources : " + source);
		System.out.println("Destination : " + destination);
		System.out.println("Date : " + date);

		model.addAttribute("name", name);
		model.addAttribute("age" + age);
		model.addAttribute("address" + address);
		model.addAttribute("source" + source);
		model.addAttribute("destination" + destination);
		model.addAttribute("date" + date);

		System.out.println("Sending Response to success.jsp");
		return "success";
	}

	@RequestMapping(value = "/train.do", method = RequestMethod.POST)
	public String searchTrain(@RequestParam String name, Model model) {
		System.out.println("Invoked SearchTrain");

		if (name != null) {
			System.out.println("Name is not null");
			if (map.containsKey(name)) {
				model.addAttribute("city", "Result found : city name is" + name);
				model.addAttribute("TraineName", "Train Name is :" + map.get(name));

			}

			return "traincheck";

		} else {
			System.out.println("Name is not found");
			model.addAttribute("result", "Result is not found");
			return "result";

		}

	}

}
