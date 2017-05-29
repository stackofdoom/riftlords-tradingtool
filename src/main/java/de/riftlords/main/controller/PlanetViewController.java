package de.riftlords.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.RawData;
import de.riftlords.main.service.PlanetViewService;



@Controller
@RequestMapping("/planets")
public class PlanetViewController {
	
	@Autowired
	private PlanetViewService planetviewService;

	
	@GetMapping("/showFormImportPlanet")
	public String addPlanet(Model theModel){
		
		theModel.addAttribute("rawdata", new RawData());
		
		return "import-planet";
	}
	
	@GetMapping("/list-planets")
	public String showPlanets(Model theModel){
		
		//get customers from dao
		List<Planet> thePlanets = planetviewService.getPlanets();
		System.out.println("Printing out " + thePlanets.size() + " planets");
				
				//add the customers to the model
		theModel.addAttribute("planets", thePlanets);
				
		return "list-planets";
	}
	
	@PostMapping("/importPlanet")
	public String importPlanet(@Validated @ModelAttribute("rawdata") RawData thePlanet, BindingResult bindingResult){
		//validate first
		 if (bindingResult.hasErrors()) {
	            return "redrect:/planets/importPlanet";
	        }
		 
		planetviewService.importPlanet(thePlanet.getText());
		
		return "redirect:/planets/list-planets";
	}
	
	
//	@ModelAttribute("planets")
//	public void getPlanets(Model model){
//		model.addAttribute(planetviewService.getPlanets());
//	}
	
//	@GetMapping("/showFormForUpdate")
//	public String updateCustomer(@RequestParam("customerId") int theId,
//									Model theModel){
//		
//		//get the customer from the service
//		Customer theCustomer=customerService.getCustomer(theId);
//		
//		
//		//set customer as a model attribute to pre-populate the form
//		theModel.addAttribute("customer", theCustomer);
//		
//		//send over to our form
//		
//		return "customer-form";
//	}
//	

}
