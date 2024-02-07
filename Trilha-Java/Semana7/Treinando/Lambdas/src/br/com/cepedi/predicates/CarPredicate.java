package br.com.cepedi.predicates;

import br.com.cepedi.model.Car;


@FunctionalInterface
public interface CarPredicate {
	boolean test(Car car);
}
