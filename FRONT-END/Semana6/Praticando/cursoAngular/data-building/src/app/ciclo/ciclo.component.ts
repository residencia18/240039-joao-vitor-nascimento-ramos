import {Input, Component, OnInit , OnChanges , DoCheck , AfterContentInit , AfterViewInit , AfterViewChecked} from '@angular/core';

@Component({
  selector: 'app-ciclo',
  templateUrl: './ciclo.component.html',
  styleUrl: './ciclo.component.css'
})
export class CicloComponent implements OnInit , OnChanges , DoCheck  , AfterContentInit , AfterViewInit , AfterViewChecked{


  @Input() valorInicial:number = 10;

  constructor() {
    this.log('constructor');
  }

  ngOnInit(){
    this.log('ngOnInit');
  }

  ngOnChanges(){
    this.log('ngOnChanges');
  }

  ngDoCheck(){
    this.log('ngDoCheck');
  }

  ngAfterContentInit(){
    this.log('ngAfterContentInit');
  }

  ngAfterViewInit(){
    this.log('ngAfterViewInit');

  }

  ngAfterViewChecked(){
    this.log('ngAfterViewChecked');

  }

  ngAfterContentChecked(){
    this.log('ngAfterContentChecked');

  }



  private log(hook:string){
    console.log(hook);
  }

}
