import { Injectable } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { AlertModelComponent } from './alert-model.component';

export enum AlertTypes {
  DANGER = 'danger',
  SUCCESS = 'sucess'
}

@Injectable({
  providedIn: 'root'
})
export class AlertModalService {
  constructor(private modalService: BsModalService) {}

  private showAlert(message: string, type: AlertTypes, dissmissTimeout?:number) {
    const bsModalRef: BsModalRef = this.modalService.show(AlertModelComponent);
    bsModalRef.content.type = type;
    bsModalRef.content.message = message;

    if(dissmissTimeout){
      setTimeout(() => bsModalRef.hide(),dissmissTimeout)
    }

  }

  showAlertDanger(message: string) {
    this.showAlert(message, AlertTypes.DANGER,2000);
  }

  showAlertSuccess(message: string) {
    this.showAlert(message, AlertTypes.SUCCESS,2000);
  }
}
