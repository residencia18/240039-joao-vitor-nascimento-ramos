import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchComponent } from './Componets/search/search.component';
import { ResultComponent } from './Componets/result/result.component';
import { HttpClientModule } from '@angular/common/http';
import { PipeBoldPipe } from './Pipes/pipe-bold.pipe';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    ResultComponent,
    PipeBoldPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
