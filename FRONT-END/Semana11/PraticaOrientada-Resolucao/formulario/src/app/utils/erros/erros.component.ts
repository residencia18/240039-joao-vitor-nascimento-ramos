import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-erros',
  templateUrl: './erros.component.html',
  styleUrl: './erros.component.css'
})
export class ErrosComponent {

  @Input() mostrarErro:any;
  @Input() msgErro:any;

}
