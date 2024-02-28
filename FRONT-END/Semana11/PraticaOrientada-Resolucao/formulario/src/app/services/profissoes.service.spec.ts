import { TestBed } from '@angular/core/testing';

import { ProfissoesService } from './profissoes.service';

describe('ProfissoesService', () => {
  let service: ProfissoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfissoesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
