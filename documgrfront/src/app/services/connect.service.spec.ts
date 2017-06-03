import { TestBed, inject } from '@angular/core/testing';

import { ConnectService } from './connect.service';

describe('ConnectService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConnectService]
    });
  });

  it('should ...', inject([ConnectService], (service: ConnectService) => {
    expect(service).toBeTruthy();
  }));
});
