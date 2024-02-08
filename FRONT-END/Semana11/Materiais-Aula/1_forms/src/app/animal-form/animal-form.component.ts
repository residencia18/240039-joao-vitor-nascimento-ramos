import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-animal-form',
  templateUrl: './animal-form.component.html',
  styleUrls: ['./animal-form.component.css']
})
export class AnimalFormComponent {

  animalForm = new FormGroup({
    id: new FormControl(''),
    age: new FormControl(''),
    color: new FormControl(''),
    type: new FormControl(''),
  });

}
