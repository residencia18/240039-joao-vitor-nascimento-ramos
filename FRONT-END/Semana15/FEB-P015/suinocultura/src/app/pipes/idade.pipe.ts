import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'idade'
})
export class IdadePipe implements PipeTransform {
  transform(birthDate: string): string {
    const [year, month, day] = birthDate.split('-').map(Number);
    const today = new Date();
    const birth = new Date(year, month - 1, day);
    
    let ageInMonths = (today.getFullYear() - birth.getFullYear()) * 12;
    ageInMonths -= birth.getMonth();
    ageInMonths += today.getMonth();
    
    if (today.getDate() < birth.getDate()) {
      ageInMonths--;
    }
    
    return `${ageInMonths} mês${ageInMonths !== 1 ? 'es' : ''}`;
  }
}