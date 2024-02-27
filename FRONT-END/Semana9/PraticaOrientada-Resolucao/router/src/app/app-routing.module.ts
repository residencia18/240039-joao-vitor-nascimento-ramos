import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RouterWikiComponent } from './applications/router-wiki/router-wiki.component';
import { RouterReaderComponent } from './applications/router-JReader/router-reader/router-reader.component';
import { RouterAppUescComponent } from './applications/router-app-uesc/router-app-uesc.component';

const routes: Routes = [
  {path:'wiki',component: RouterWikiComponent},
  {path:'reader',component:RouterReaderComponent},
  {path:'uesc',component:RouterAppUescComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
