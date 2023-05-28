package com.aurora.webgames.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List; 
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.aurora.webgames.model.CadastroAuroraModel;
import com.aurora.webgames.model.JogoAuroraModel;
import com.aurora.webgames.model.UsuarioAuroraModel;
import com.aurora.webgames.repository.CategoriasAurora;
import com.aurora.webgames.repository.JogosAurora;
import com.aurora.webgames.repository.UsersAurora;
import com.aurora.webgames.service.CategoriasAuroraService;
import com.aurora.webgames.service.JogosAuroraService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@Controller
@RequestMapping("/aurora/jogos")
public class JogoAuroraController {

	private static final String CADASTRA_JOGO = "cadastroJogo";
	
	@Autowired
	private JogosAurora jogosAurora;
	
	@Autowired
	private CategoriasAurora categoriasAurora;

	@Autowired
	private UsersAurora usersAurora;

	@Autowired
	private JogosAuroraService jogosService;
	
	@Autowired
	private CategoriasAuroraService catAuroraService;
	
	//Jogos

	@RequestMapping("/cadastrar")
	public ModelAndView cadastroNovo() {
		List<CadastroAuroraModel> allCategorias = categoriasAurora.findAll();
		ModelAndView mv = new ModelAndView(CADASTRA_JOGO);
		mv.addObject("categoriasAurora", allCategorias);		
		mv.addObject(new JogoAuroraModel());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarCadastro(@RequestParam("imagemJogo") MultipartFile file, @Validated JogoAuroraModel jogoAuroraModel, Errors errors, RedirectAttributes attributes) {

		try {
			jogoAuroraModel.setImgJogo(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (errors.hasErrors()){
			return CADASTRA_JOGO;
		}
		jogosAurora.save(jogoAuroraModel);
		attributes.addFlashAttribute("mensagem","Jogo salvo com sucesso!");
		return "redirect:/aurora/jogos/cadastrar";
	}

	@RequestMapping("/listar")
	public ModelAndView mostraCadastro() {
		List<JogoAuroraModel> allJogos = jogosAurora.findAll();
		ModelAndView mv = new ModelAndView("jogosCadastrados");
		mv.addObject("JogosAurora", allJogos);
		return mv;
	}

	@RequestMapping("/inicio")
	public ModelAndView telaInicio() {
		List<JogoAuroraModel> allJogos = jogosAurora.findAll();
		ModelAndView mv = new ModelAndView("inicio");
		mv.addObject("JogosAurora", allJogos);
		return mv;
	}
	
	@RequestMapping("/editar/{cdJogo}")
	public ModelAndView editarCadastro(@PathVariable Long cdJogo) {
		Optional<JogoAuroraModel> jogoAurora = jogosAurora.findById(cdJogo);
		List<CadastroAuroraModel> allCategorias = categoriasAurora.findAll();
		ModelAndView mv = new ModelAndView(CADASTRA_JOGO);
		mv.addObject("categoriasAurora", allCategorias);	
		mv.addObject("jogoAuroraModel", jogoAurora.get());																																																																																
		return mv;	
	}

	@RequestMapping("/avaliar/{cdJogo}")
	public ModelAndView avaliarCadastro(@PathVariable Long cdJogo) {
		Optional<JogoAuroraModel> jogoAurora = jogosAurora.findById(cdJogo);
		List<CadastroAuroraModel> allCategorias = categoriasAurora.findAll();
		ModelAndView mv = new ModelAndView("cadastroJogoAvaliar");
		mv.addObject("categoriasAurora", allCategorias);	
		mv.addObject("jogoAuroraModel", jogoAurora.get());																																																																																
		return mv;	
	}
	
	@RequestMapping("/jogar/{cdJogo}")
	public ModelAndView jogarJogo(@PathVariable Long cdJogo) {
		Optional<JogoAuroraModel> jogoAurora = jogosAurora.findById(cdJogo);
		ModelAndView mv = new ModelAndView("jogoSelecionado");
		mv.addObject("jogoAuroraModel", jogoAurora.get());																																																																																
		return mv;	
	}
	
	@RequestMapping(value = "/deletar", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String excluirJogo(@RequestParam(name = "cdJogo") Long cdJogo) {
		jogosAurora.deleteById(cdJogo);
		return "redirect:/aurora/jogos/listar";
	}

	@GetMapping("/imagem/{cdJogo}")
	@ResponseBody
	public byte[] mostraImagem(@PathVariable("cdJogo") Long cdJogo) {
		JogoAuroraModel jogoAuroraModel = this.jogosAurora.getOne(cdJogo);
		return jogoAuroraModel.getImgJogo();
	}

	//Categorias

	@RequestMapping("/categoria/cadastrar")
	public ModelAndView cadastroNovaCat() {
		ModelAndView mv = new ModelAndView("cadastraCategoria");		
		mv.addObject(new CadastroAuroraModel());
		return mv;
	}

	@RequestMapping(value = "/categoria", method = RequestMethod.POST)
	public String salvarCadastroCategoria(@Validated CadastroAuroraModel cadastroAuroraModel, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()){
			return "cadastraCategoria";
		}
		categoriasAurora.save(cadastroAuroraModel);
		attributes.addFlashAttribute("mensagem","categoria salva com sucesso!");
		return "redirect:/aurora/jogos/categoria/cadastrar";
	}

	@RequestMapping("/categoria/listar")
	public ModelAndView mostraCadastroCat() {
		List<CadastroAuroraModel> allCat = categoriasAurora.findAll();
		ModelAndView mv = new ModelAndView("categoriasCadastradas");
		mv.addObject("CategoriasAurora", allCat);
		return mv;
	}
	
	@RequestMapping("/categoria/editar/{cdCat}")
	public ModelAndView editarCadastroCat(@PathVariable Long cdCat) {
		Optional<CadastroAuroraModel> catAurora = categoriasAurora.findById(cdCat);
		ModelAndView mv = new ModelAndView("cadastraCategoria");
		mv.addObject("cadastroAuroraModel", catAurora.get());																																																																																
		return mv;	
	}
	
	
	@RequestMapping(value = "/categoria/deletar", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String excluirCat(@RequestParam(name = "cdCat") Long cdCat) {
		categoriasAurora.deleteById(cdCat);
		return "redirect:/aurora/jogos/categoria/listar";
	}

	//Login

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	@RequestMapping(value="/",method = RequestMethod.POST)
	public String loginAuth(@ModelAttribute(name="usuarioAuroraModel") UsuarioAuroraModel usuarioAuroraModel, Model model) {
		String nome = usuarioAuroraModel.getNome();
		String senhaUser = usuarioAuroraModel.getSenhaUser();
		if("pedro".equals(nome) && "franca".equals(senhaUser)){
			return "redirect:/aurora/jogos/inicio";
		}
		model.addAttribute("invalidCredentials", true);
		return "index";
	}

	@RequestMapping("/user/cadastrar")
	public ModelAndView cadastroNovoUser() {
		ModelAndView mv = new ModelAndView("cadastroUser");		
		mv.addObject(new UsuarioAuroraModel());
		return mv;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String salvarCadastroUser(@Validated UsuarioAuroraModel usuarioAuroraModel, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()){
			return "cadastroUser";
		}
		usersAurora.save(usuarioAuroraModel);
		attributes.addFlashAttribute("mensagem","usuario salvo com sucesso!");
		return "redirect:/aurora/jogos/user/cadastrar";
	}

	@RequestMapping("/user/listar")
	public ModelAndView mostraCadastroUser() {
		List<UsuarioAuroraModel> allUsers = usersAurora.findAll();
		ModelAndView mv = new ModelAndView("userCadastrados");
		mv.addObject("UsersAurora", allUsers);
		return mv;
	}
	
	@RequestMapping("/user/editar/{cdUser}")
	public ModelAndView editarCadastroUser(@PathVariable Long cdUser) {
		Optional<UsuarioAuroraModel> userAurora = usersAurora.findById(cdUser);
		ModelAndView mv = new ModelAndView("cadastroUser");
		mv.addObject("usuarioAuroraModel", userAurora.get());																																																																																
		return mv;	
	}
	
	
	@RequestMapping(value = "/user/deletar", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String excluirUser(@RequestParam(name = "cdUser") Long cdUser) {
		categoriasAurora.deleteById(cdUser);
		return "redirect:/aurora/jogos/categoria/listar";
	}

}
