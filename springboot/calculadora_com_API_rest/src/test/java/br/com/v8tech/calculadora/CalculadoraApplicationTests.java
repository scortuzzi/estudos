package br.com.v8tech.calculadora;

import br.com.v8tech.calculadora.domain.Calculo;
import br.com.v8tech.calculadora.domain.DadosCalculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class CalculadoraApplicationTests {

	@Test
	void doisMaisTres() throws Exception {

		DadosCalculo dados = new DadosCalculo(2.0, '+', 3.0);

		Calculo calculo = new Calculo(dados);
		Double resposta = calculo.getResultado();

		Assertions.assertEquals(5.0, resposta);
	}

	@Test
	void QuatroMenosTres() throws Exception {

		DadosCalculo dados = new DadosCalculo(4.0, '-', 3.0);

		Calculo calculo = new Calculo(dados);
		Double resposta = calculo.getResultado();

		Assertions.assertEquals(1.0, resposta);
	}

	@Test
	void TresVezesQuatro() throws Exception {
		DadosCalculo dados = new DadosCalculo(3.0, '*', 4.0);

		Calculo calculo = new Calculo(dados);
		Double resposta = calculo.getResultado();

		Assertions.assertEquals(12.0, resposta);

	}

	@Test
	void OitoDivididoPorDois() throws Exception {
		DadosCalculo dados = new DadosCalculo(8.0, '/', 2.0);

		Calculo calculo = new Calculo(dados);
		Double resposta = calculo.getResultado();

		Assertions.assertEquals(4.0, resposta);

	}

	@Test
	void QuatroElevadoSete() throws Exception {
		DadosCalculo dados = new DadosCalculo(4.0, '^', 7.0);

		Calculo calculo = new Calculo(dados);
		Double resposta = calculo.getResultado();

		Assertions.assertEquals(16384.0, resposta);

	}



	@Autowired
	private MockMvc mvc;

	@Test
	void BadReq() throws Exception {

		//passando um char inválido como operador deveria retornar um exception 400

		String json = """
   				{
				"numero01": "10.0",
				"operador": "a",
				"numero02": 4.0
				}""";

		var response = mvc.perform(
				post("/calculo").content(json).contentType(MediaType.APPLICATION_JSON)
		).andReturn().getResponse();


		Assertions.assertEquals(400, response.getStatus());
	}

}
