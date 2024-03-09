import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppointmentListComponent } from './components/atendimentos-lista/appointment-list.component';
import { DetailsComponent } from './components/details/details.component';
import { FormComponent } from './components/form/form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './login/login/login.component';
import { AuthGuard } from './guards/guard.guard';

const rotasApp: Routes = [
  { path: 'cadastro', component: FormComponent , canActivate: [AuthGuard] },
  { path: 'lista', component: AppointmentListComponent , canActivate: [AuthGuard] },
  { path: 'detalhe/:id', component: DetailsComponent , canActivate: [AuthGuard] },
  { path: 'editar/:id', component: FormComponent , canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: LoginComponent },
  { path: '**', redirectTo: '/login' }
];


@NgModule({
  declarations: [
    AppComponent,
    AppointmentListComponent,
    DetailsComponent,
    FormComponent,
    HeaderComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot(rotasApp),
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
