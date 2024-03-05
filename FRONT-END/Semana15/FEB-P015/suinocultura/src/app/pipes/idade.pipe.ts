import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'idade'
})
export class IdadePipe implements PipeTransform {

  transform(birthDate: string): string {
    const [day, month, year] = birthDate.split('-').map(Number);
    const today = new Date();
    const birth = new Date(year, month - 1, day);
    const ageInMonths = (today.getMonth() - birth.getMonth()) + 12 * (today.getFullYear() - birth.getFullYear());
    return `${ageInMonths} meses`;
  }

}
