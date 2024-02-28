import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  private formDataSubject = new BehaviorSubject<{ data: any, status: string }>({ data: null, status: '' });

  constructor() { }

  setFormData(formData: FormGroup) {
    formData.valueChanges.subscribe(data => {
      this.formDataSubject.next({ data, status: formData.status });
    });

    formData.statusChanges.subscribe(status => {
      this.formDataSubject.next({ data: formData.value, status });
    });
  }

  getFormData() {
    return this.formDataSubject.asObservable();
  }
}
