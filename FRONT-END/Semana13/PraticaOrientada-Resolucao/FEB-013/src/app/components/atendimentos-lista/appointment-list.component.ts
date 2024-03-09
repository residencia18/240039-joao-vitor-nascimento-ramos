import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ServicoDadosAtendimento } from '../../Services/appointment-data.service'
import { Atendimento } from '../../Model/Atendimento';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrl: './appointment-list.component.css'
})
export class AppointmentListComponent {
  listaAtendimentos: Atendimento[] = [];
  detailShown: string | null = null;
  listaAtendimentos$: Observable<Atendimento[]> | undefined;

  constructor(private dataService: ServicoDadosAtendimento, private rota: Router) { }

  showDetails(id: string) {
    if (!this.detailShown || this.detailShown !== id)
      this.detailShown = id;
    else
      this.detailShown = null;
  }

  editAppointment(id: string) {
    this.rota.navigate(['/editar', id]);
  }

  ngOnInit() {
    // pegando os atendimentos


    this.dataService.obterAtendimentos().subscribe(
      {
        next: (response) => {
          for (let key in response) {
            this.listaAtendimentos.push({ ...response[key], id: key});
          }
        },
        error: (error) => {
          console.log(error);
        }
      }
    );

  }




}
