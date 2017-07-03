package spittr.web;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.Spittle;
import spittr.data.SpittleRepository;

public class SpittleControllerTest {
	@Test
	public void shouldShowReXcentSpitttles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(20);
		//mock repository
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
				/*This is so the mock framework won¡¯t try to resolve the view name
				coming from the controller on its own. In many cases, this is unnecessary. But for this
				controller method, the view name will be similar to the request¡¯s path; left to its
				default view resolution, MockMvc will fail because the view path will be confused with
				the controller¡¯s path*/				
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.build();
		mockMvc.perform(MockMvcRequestBuilders.get("/spittleControllerMapping"))
		.andExpect(MockMvcResultMatchers.view().name("spittles"))
		/* When addAttribute() is called without specifying a key, the key is inferred from the type of 
		 * object being set as the value. In this case, because it¡¯s a List<Spittle>, the key will be 
		 * inferred as spittleList*/
		.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
		.andExpect(MockMvcResultMatchers.model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
		
		//================================================================================
		
		mockMvc.perform(MockMvcRequestBuilders.get("/spittleControllerMapping/v1"))
		.andExpect(MockMvcResultMatchers.view().name("spittles"))
		/* When addAttribute() is called without specifying a key, the key is inferred from the type of 
		 * object being set as the value. In this case, because it¡¯s a List<Spittle>, the key will be 
		 * inferred as spittleList*/
		.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
		.andExpect(MockMvcResultMatchers.model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
		
		//================================================================================
		
		mockMvc.perform(MockMvcRequestBuilders.get("/spittleControllerMapping/v2"))
		.andExpect(MockMvcResultMatchers.view().name("spittleControllerMapping/v2"))
		/* When addAttribute() is called without specifying a key, the key is inferred from the type of 
		 * object being set as the value. In this case, because it¡¯s a List<Spittle>, the key will be 
		 * inferred as spittleList*/
		.andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
		.andExpect(MockMvcResultMatchers.model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
}
