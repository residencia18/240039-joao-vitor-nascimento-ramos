import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Usuario } from './usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  usuario = new Usuario();

  constructor(private auth:AuthService){

  }

  fazerLogin(){
    this.auth.fazerLogin(this.usuario)
  }

  ngOnInit(){

  }

}
