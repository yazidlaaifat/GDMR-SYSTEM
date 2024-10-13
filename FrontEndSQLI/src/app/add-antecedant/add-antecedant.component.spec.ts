import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAntecedantComponent } from './add-antecedant.component';

describe('AddAntecedantComponent', () => {
  let component: AddAntecedantComponent;
  let fixture: ComponentFixture<AddAntecedantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAntecedantComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAntecedantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
