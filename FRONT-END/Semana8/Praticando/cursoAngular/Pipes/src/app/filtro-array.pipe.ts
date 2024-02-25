import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filtroArray'
})
export class FiltroArrayPipe implements PipeTransform {

  transform(value: any[], args?: string): any[] {
    if (!value || value.length === 0 || !args) {
      return value;
    }

    const filter = args.toLowerCase();
    return value.filter(v => v.toLowerCase().indexOf(filter) !== -1);
  }

}
