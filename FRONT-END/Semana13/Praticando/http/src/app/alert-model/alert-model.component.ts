import { Component, Input } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-alert-model',
  templateUrl: './alert-model.component.html',
  styleUrl: './alert-model.component.css'
})
export class AlertModelComponent {

  @Input() type = 'success';
  @Input() message: string = "";

  constructor(public bsModalRef: BsModalRef) { }

  ngOnInit() {
  }

  onClose() {
    this.bsModalRef.hide();
  }
}
