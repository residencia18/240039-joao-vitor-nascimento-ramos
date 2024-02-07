package br.com.cepedi.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import br.com.cepedi.model.Car;

public class LambdaTeste01 {
	
	public static void main(String[] args) {
		
		List<Car> carros = new ArrayList<>();
		Car c1 = new Car("green",1972);
		Car c2 = new Car("red",1978);
		Car c3 = new Car("blue",1974);
		carros.add(c1);
		carros.add(c2);
		carros.add(c3);
		imprimeList(carros, (Car car) -> System.out.println(car));
		
	}
	
	
	public static <T> void imprimeList(List<T> list , Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}

}
