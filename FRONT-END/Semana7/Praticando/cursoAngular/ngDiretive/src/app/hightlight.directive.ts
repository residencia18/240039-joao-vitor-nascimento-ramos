import { Directive , HostListener ,HostBinding , Input} from '@angular/core';

@Directive({
  selector: '[appHightlight]'
})
export class HightlightDirective {

  constructor() { }

  @HostListener('mouseenter') onMouseOver(){
    this.backgroundColor =  this.color
  }

  @HostListener('mouseleave') onMouseLeave(){
    // this.render.setStyle(this.element.nativeElement,'background-color','white');
    this.backgroundColor = this.defaultColor
  }

  @HostBinding('style.backgroundColor') backgroundColor:string ='';

  @Input() defaultColor:string = 'white';
  @Input('appHightlight') color:string = 'yellow';

  ngOnInit(){
    this.backgroundColor= this.defaultColor;
  }

}
