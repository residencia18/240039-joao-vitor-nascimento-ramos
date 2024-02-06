package br.com.cepedi;

import java.util.ArrayList;
import java.util.List;

public class ComportamentoPorParametroTest01 {
	
	private List<Car> cars = new ArrayList<Car>(List.of(new Car("green",2011), new Car("black",1998),new Car("Yellow",2012))); 
	
	
	
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	private static List<Car> filterByColor(List<Car> carros, String cor){
		List<Car> greenCars = new ArrayList();
		for (Car car : carros) {
			if(car.getColor().equals(cor)){
				greenCars.add(car);
			}
		}
		return greenCars;
	}
	
	public static void main(String[] args) {
		ComportamentoPorParametroTest01 c = new ComportamentoPorParametroTest01();
		System.out.println(filterByColor(c.getCars(),"green"));
	}

}
