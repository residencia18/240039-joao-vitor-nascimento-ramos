import { Directive ,ElementRef , Renderer2} from '@angular/core';

@Directive({
  selector: '[appNovinha]'
})
export class NovinhaDirective {

  constructor(private color:ElementRef , private renderer: Renderer2) {
    this.renderer.setStyle(this.color.nativeElement,'background-color','red');
  }

}
