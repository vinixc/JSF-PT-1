package br.com.vini.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.vini.livraria.dao.LivroDao;
import br.com.vini.livraria.entity.Livro;
import br.com.vini.livraria.entity.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable{
	private static final long serialVersionUID = 2358293964529134200L;
	
	@Inject
	private LivroDao livroDao;

	public List<Venda> getVendas(int i){
		List<Venda> vendas = new ArrayList<Venda>();
		List<Livro> livros = livroDao.listaTodos();
		
		Random random = new Random(i);
		for(Livro livro : livros) {			
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}
		
		return vendas;
	}
	
	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		model.setTitle("Vendas");
		model.setLegendPosition("ne");
		
		Axis xAxis = model.getAxis(AxisType.X);
	    xAxis.setLabel("Titulo");
	    
	    Axis yAxis = model.getAxis(AxisType.Y);
	    yAxis.setLabel("Quantidade");
		
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2020");
		
		List<Venda> vendas = getVendas(4321);
		for(Venda venda : vendas ) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		
		ChartSeries vendaSerie19 = new ChartSeries();
		vendaSerie19.setLabel("Vendas 2019");
		
		vendas = getVendas(1234);
		
		for(Venda venda : vendas ) {
			vendaSerie19.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		model.addSeries(vendaSerie);
		model.addSeries(vendaSerie19);
		return model;
	}

}
