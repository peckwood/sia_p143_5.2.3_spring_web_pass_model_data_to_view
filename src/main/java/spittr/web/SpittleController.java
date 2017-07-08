package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittleControllerMapping")
public class SpittleController {
	private SpittleRepository spittleRepository;
	
	@Autowired
	public SpittleController(SpittleRepository spittleRepository){
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model){
		//add spittles to model
		/*When addAttribute() is called without specifying a key, the key is inferred from the type of 
		 * object being set as the value. In this case, because it��s a List<Spittle>, the key will be 
		 * inferred as spittleList
		 * */
		List<Spittle> spittles = spittleRepository.findSpittles(Long.MAX_VALUE, 20);
		System.out.println("spittles: "+ spittles);
		model.addAttribute("spittleList", spittles);
		//return view name
		return "spittles";
	}
	
	//same as spittles
	@RequestMapping(method = RequestMethod.GET, value="/v1")
	public String spittles1(Model model){
		List<Spittle> spittles = spittleRepository.findSpittles(Long.MAX_VALUE, 20);
		System.out.println("spittles: "+ spittles);
		model.addAttribute(spittles);
		//return view name
		return "spittles";
	}
	
	/*
	 * This version is quite a bit different from the others. Rather than return a logical view
	 * 	name and explicitly setting the model, this method returns the Spittle list. When a
	 * 	handler method returns an object or a collection like this, the value returned is put
	 * 	into the model, and the model key is inferred from its type (spittleList, as in the
	 * 	other examples).
	 * 	As for the logical view name, it��s inferred from the request path. Because this
	 * 	method handles GET requests for /spittles, the view name is spittle/v2 (chopping off
	 * 	the leading slash)
	 */
	//view name: spittleControllerMapping/v2 (request path)
	//model name: spittleList
	@RequestMapping(method = RequestMethod.GET, value="/v2")
	public List<Spittle> spittles2() {
		return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
	}
}