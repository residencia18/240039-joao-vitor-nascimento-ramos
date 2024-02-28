import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-debug',
  templateUrl: './debug.component.html',
  styleUrl: './debug.component.css'
})
export class DebugComponent {

  @Input() form:any ;

  constructor(){

  }

  ngOnInit(): void {
    
  }

}
