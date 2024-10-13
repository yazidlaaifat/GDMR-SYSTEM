import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AntecedantService} from "../Services/antecedant.service";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../Services/auth.service";

@Component({
  selector: 'app-add-antecedant',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './add-antecedant.component.html',
  styleUrl: './add-antecedant.component.css'
})
export class AddAntecedantComponent implements OnInit{
  @Input() dossierMedicalId!: number;
  @Output() close = new EventEmitter<void>();
  antecedantForm: FormGroup;
  currentUser: any = {};

  constructor(
    private antecedantService: AntecedantService,
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.antecedantForm = this.fb.group({
      dateAntecedant: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.antecedantForm.valid) {
      const antecedantData = {
        ...this.antecedantForm.value,
        dossierMedicalId: this.dossierMedicalId
      };
      this.antecedantService.addAntecedant(antecedantData).subscribe(() => {
        this.close.emit();
      });
    }
    window.location.reload();
  }

  onClose(): void {
    this.close.emit();
  }

  ngOnInit(): void {
    this.loadCurrentUser();
  }

  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
      },
      error: error => {

      }
    });
  }
}
