import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WikiModule } from './applications/router-wiki/wiki.module';
import { JReaderModule } from './applications/router-JReader/reader.module';
import { AppUescModule } from './applications/router-app-uesc/router-app-uesc.module';

@NgModule({
  declarations: [
    AppComponent,
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    WikiModule,
    JReaderModule,
    AppUescModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
