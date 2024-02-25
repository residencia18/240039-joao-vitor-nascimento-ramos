import { Directive , HostListener , ElementRef , Renderer2, HostBinding } from '@angular/core';

@Directive({
  selector: '[HightightMouse]'
})
export class HightightMouseDirective {

  @HostListener('mouseenter') onMouseOver(){
    // this.render.setStyle(this.element.nativeElement,'background-color','red');
    this.backgroundColor = 'yellow';
  }

  @HostListener('mouseleave') onMouseLeave(){
    // this.render.setStyle(this.element.nativeElement,'background-color','white');
    this.backgroundColor = 'white'
  }

  @HostBinding('style.backgroundColor') backgroundColor:string ='';

  // constructor(private element:ElementRef , 
  //             private render : Renderer2) { }


}
