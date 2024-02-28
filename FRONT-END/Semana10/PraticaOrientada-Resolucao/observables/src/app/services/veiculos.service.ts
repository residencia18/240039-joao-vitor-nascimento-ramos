import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Veiculo } from '../modelos/Veiculo';
import { Categorias } from '../modelos/Categorias';

@Injectable({
  providedIn: 'root'
})
export class VeiculosService {

  json: Veiculo[][] = [];

    //abre o arquivo json para leitura
    onAbrirArquivo(name: string): Observable<string> {
      this.carregarArquivoJSON(name);
      return of(name);
    }

  //busca veiculos no json
  carregarArquivoJSON(caminho: string) {
    this.http.get<Categorias>('../assets\\' + caminho).subscribe((json) => {
      this.json = [json.Avioes, json.Carros, json.Barcos]; //realiza eitura do json
      this.showTiposSubject.next(true); // se aberto com sucesso, mostra as 3 classes na view
    });
  }

  constructor(private http:HttpClient) { }

  //subject e observable para mostrar classes
  private showTiposSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  showTipos$ = this.showTiposSubject.asObservable();

  private showValorComponentSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  showValorComponent$ = this.showValorComponentSubject.asObservable();

  //subject e observable do tipo do veiculo
  private tipoVeiculoSelecionadoSubject = new BehaviorSubject<string>('');
  tipoVeiculoSelecionado$ = this.tipoVeiculoSelecionadoSubject.asObservable();

  //subject e observable do tipo da lista de veiculos do tipo
  private veiculosSelecionadosSubject = new BehaviorSubject<Veiculo[]>([]);
  veiculosSelecionados$ = this.veiculosSelecionadosSubject.asObservable();

  //subject e observable do indice da escolha do tipo do veiculo
  private indexVeiculoSelecionadoSubject = new BehaviorSubject<number>(-1);
  indexSelecionado$ = this.indexVeiculoSelecionadoSubject.asObservable();

  //subject e observablo do veiculo selecionado
  private veiculoSelecionadoSubject = new BehaviorSubject<any>({});
  veiculoSelecionado$ = this.veiculoSelecionadoSubject.asObservable();

  //subject e observable do indice da escolha do tipo da propriedade
  private indexPropriedadeSelecionadaSubject = new BehaviorSubject<number>(-1);
  indexPropriedadeSelecionada$ = this.indexPropriedadeSelecionadaSubject.asObservable();

  private propriedadeSelecionadaSubject = new BehaviorSubject<string>('');
  propriedadeSelecionada$ = this.propriedadeSelecionadaSubject.asObservable();

  private listaVeiculosAdicionadosSubject = new BehaviorSubject<Veiculo[]>([]);
  listaVeiculosAdicionados$ = this.listaVeiculosAdicionadosSubject.asObservable();

  //metodo para selecionar tipo de veiculo
  selecionarTipoVeiculo(tipo:string): void{
    this.tipoVeiculoSelecionadoSubject.next(tipo);
    this.getVeiculos(tipo);
  }

  //metodo interno que seleciona veiculos pelo tipo
  getVeiculos(tipo:string): void{
    let veiculos: Veiculo[] = [];
    switch(tipo){
      case 'Aviões':
        veiculos = this.json[0]; //lista so de aviões
        break;
      case 'Carros':
        veiculos = this.json[1]; //lista só de carros
        break;
      case 'Barcos':
        veiculos = this.json[2]; //lista só de barcos
        break
      default:
        break;
    }
    //atualiza o subject
    this.veiculosSelecionadosSubject.next(veiculos);
  }


  //metodo para selecionar propriedade de veiculo
  selecionarPropriedade(index: number){
    this.indexPropriedadeSelecionadaSubject.next(index);
    this.setPropriedadeSelecionada(index);
    console.log(this.propriedadeSelecionada$)
    this.showValorComponentSubject.next(true);
  }

  selecionarveiculo(index: number){
    this.indexVeiculoSelecionadoSubject.next(index);
    let temp;
    this.veiculosSelecionados$.subscribe(veiculo => temp = veiculo[index]);
    this.setVeiculoSelecionado(temp!);
  }

  setVeiculoSelecionado(veiculo: Veiculo){
    this.veiculoSelecionadoSubject.next(veiculo);
  }

  //metodo interno que seleciona propriedade do veiculo
  setPropriedadeSelecionada(index: number){
    let propriedade:string;
    switch(index){
      case 0:
        this.veiculoSelecionado$.subscribe(veiculo => {
          propriedade = veiculo.Name;
        })
        break;
      case 1:
        this.veiculoSelecionado$.subscribe((veiculo) => {
          propriedade = veiculo.Model;
        });
        break;
      case 2:
        this.veiculoSelecionado$.subscribe((veiculo) => {
          propriedade = veiculo.Engine;
        });
        break;
      case 3:
        this.veiculoSelecionado$.subscribe((veiculo) => {
          propriedade = veiculo.NumberOfPassengers;
        });
        break;
      case 4:
        this.veiculoSelecionado$.subscribe((veiculo) => {
          propriedade = veiculo.Autonomia;
        });
        break;
      case 5:
        this.veiculoSelecionado$.subscribe((veiculo) => {
          propriedade = veiculo.Alcance;
        });
        break;
      case 6:
        this.veiculoSelecionado$.subscribe((veiculo) => {
          propriedade = veiculo.Teto;
        });
        break;
      default:
        break;
    }
    this.propriedadeSelecionadaSubject.next(propriedade!);
  }


  //adicionar veiculo a lista
  adicionarVeiculo(veiculo: Veiculo) {
    const listaAtual = this.listaVeiculosAdicionadosSubject.value;

    if (!listaAtual.some((item) => veiculo.Name === item.Name)){
      listaAtual.push(veiculo);
    }
    this.listaVeiculosAdicionadosSubject.next(listaAtual);
  }



}
