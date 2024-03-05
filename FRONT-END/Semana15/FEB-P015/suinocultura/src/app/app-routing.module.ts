import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CadastroSuinoComponent } from './components/cadastro-suino/cadastro-suino.component';
import { ListagemSuinosComponent } from './components/listagem-suinos/listagem-suinos.component';

const routes: Routes = [
  { path: '', redirectTo: '/listagem', pathMatch: 'full' },
  {path: 'cadastro', component: CadastroSuinoComponent},
  {path: 'listagem', component: ListagemSuinosComponent},
  // { path: 'home', loadChildren: './home/home.module#HomePageModule' },
  // { path: 'cadastro', loadChildren: './cadastro/cadastro.module#CadastroPageModule' },
  // { path: 'login', loadChildren: './login/login.module#LoginPageModule' },
  // { path: 'cadastro-sucesso', loadChildren: './cadastro-sucesso/cadastro-sucesso.module#CadastroSucessoPageModule' },
  // { path: 'login-sucesso', loadChildren: './login-sucesso/login-sucesso.module#LoginSucessoPageModule' },
  // { path: 'cadastro-erro', loadChildren: './cadastro-erro/cadastro-erro.module#CadastroErroPageModule' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
