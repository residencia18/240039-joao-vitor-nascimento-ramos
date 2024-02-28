import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TemplateFormComponent } from './components/template-form/template-form.component';
import { DataFormComponent } from './components/data-form/data-form.component';

const routes: Routes = [
  {path:'templateForm', component:TemplateFormComponent},
  {path:'dataForm', component:DataFormComponent},
  {path:'',pathMatch:'full',redirectTo: 'dataForm'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
