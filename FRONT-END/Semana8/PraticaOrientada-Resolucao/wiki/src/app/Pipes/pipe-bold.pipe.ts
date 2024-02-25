import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'pipeBold'
})
export class PipeBoldPipe implements PipeTransform {

  transform(value: string, filtro: string): any {
    if (!value || !filtro) {
      return value;
    }
  
    const regex = new RegExp(filtro, 'gi');
  
    const textoFormatado = value.replace(regex, (match) => `<b>${match}</b>`);
  
    return textoFormatado;
  }

}
  
