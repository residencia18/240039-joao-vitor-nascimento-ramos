package br.com.cepedi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ParametrizaTest02 {
	
	private static List<Car> cars = new ArrayList<Car>(List.of(new Car("green",2011), new Car("black",1998),new Car("Yellow",2012))); 
	
	
	
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
	public static void main(String[] args) {
//		List<Car> greenCars= filter(cars,new Predicate() {
//			@Override
//			public boolean test(Car car) {
//				return car.getColor().equals("green");
//			}
//		});
		
		List<Car> greenCars = filter(cars, car -> car.getColor().equals("green"));
		List<Car> redCars = filter(cars, car -> car.getColor().equals("red"));
		List<Car> yearBefore2015 = filter(cars, car -> car.getYear() < 2002);

		System.out.println(greenCars);
		System.out.println(redCars);

		System.out.println(yearBefore2015);

		
	}
	
	
	private static <T> List<T>  filter(List<T> lista, Predicate<T> predicate){
		List<T> listaFiltrada = new ArrayList<>();
		for(T e : lista) {
			if(predicate.test(e)) {
				listaFiltrada.add(e);
			}
		}
		
		return listaFiltrada;
	}
	

}
