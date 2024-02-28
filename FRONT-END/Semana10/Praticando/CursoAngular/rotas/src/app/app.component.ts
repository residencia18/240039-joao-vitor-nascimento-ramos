import { AuthService } from './login/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'rotas';

  mostraMenu: boolean = false;

  constructor(private authService:AuthService){

  }


  ngOnInit(): void {
    this.authService.mostrarMenuEmitter.subscribe( 
      mostrar => this.mostraMenu=mostrar
    )
  }
}
