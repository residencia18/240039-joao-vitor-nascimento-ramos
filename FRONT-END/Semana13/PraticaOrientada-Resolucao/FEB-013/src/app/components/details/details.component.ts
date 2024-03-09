import { Component, Input } from '@angular/core';
import { ServicoDadosAtendimento } from '../../Services/appointment-data.service';
import { Atendimento } from '../../Model/Atendimento';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  @Input() atendimentoID: string = '';
  atendimento: Atendimento = {} as Atendimento;

  constructor(private dataService: ServicoDadosAtendimento) { }
  
  ngOnInit() {
    this.dataService.obterAtendimento(this.atendimentoID).subscribe(
      {
        next: (response) => {
          this.atendimento = response;
        },
        error: (error) => {
          console.log(error);
        }
      }
    );
  }
  
}
