import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DebugComponent } from './debug/debug.component';
import { ErrosComponent } from './erros/erros.component';



@NgModule({
  declarations: [DebugComponent,ErrosComponent],
  imports: [
    CommonModule
  ],
  exports:[
    DebugComponent,
    ErrosComponent
  ]
})
export class UtilsModule { }
