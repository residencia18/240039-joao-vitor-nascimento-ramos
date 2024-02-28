import { Component , Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-form-debug',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent  implements OnInit{

  @Input() form:any ;

  constructor(){

  }

  ngOnInit(): void {
    
  }

}
