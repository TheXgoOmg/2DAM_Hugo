# -*- coding: utf-8 -*-

from datetime import date
from dateutil.relativedelta import relativedelta
from odoo import models, fields, api
from odoo.exceptions import ValidationError

class HospitalPacientes(models.Model):

    _name = 'hospital.pacientes'

    _description = 'Pacientes del Hospital'

    _order = 'nombre'

    _rec_name = 'nombre'

    # === CAMPOS ===

    nombre = fields.Many2one(
        'res.partner',
        string='Nombre',
        required=True,
    )

    apellidos = fields.Char('Apellidos')

    dni = fields.Char('DNI/Nº Pasaporte') # Atributo related con 'vat' de res.partner

    sip = fields.Char('SIP')

    historia_clinica = fields.Char(
        string='Nº Historia clínica',
        required=True,
        copy=False,
        readonly=True,
        default='Nuevo'
    )

    fecha_nacimiento = fields.Date('Fecha de Nacimiento')

    edad = fields.Integer(string='Edad', compute='_compute_edad', store=True)

    sexo = fields.Selection([
        ('hombre', 'Hombre'),
        ('mujer', 'Mujer'),
        ('otros', 'Otros')
    ], string='Sexo', default='otros')

    raza = fields.Selection([
        ('caucasica', 'Caucásica'),
        ('negroide', 'Negroide'),
        ('amarilla', 'Amarilla'),
        ('amerindia', 'Amerindia')
    ], string='Raza')

    estado_civil = fields.Selection([
        ('soltero', 'Soltero'),
        ('casado', 'Casado'), 
        ('divorciado','Divorciado'),
        ('viudo', 'Viudo')
    ], string='Estado Civil')

    persona_contacto = fields.Many2one(
        'res.partner',
        string='Persona de Contacto'
    )

    fecha_alta = fields.Date(
        string='Fecha alta', 
        default=fields.Date.context_today,
        readonly=True
    )

    fecha_baja = fields.Date(
        string='Fecha baja'
    )

    motivo_baja = fields.Selection([
        ('defuncion', 'Defunción'),
        ('traslado', 'Traslado'),
        ('otros', 'Otros')
    ])

    enfermedades = fields.Char(
        string='Enfermedades crónicas, alergias, intolerancias'
    )

    medicacion_cronica = fields.Char(
        string='Medicación crónica'
    )

    antecedentes = fields.Char(
        string='Antecedentes familiares'
    )

    intervenciones = fields.Char(
        string='Intervenciones quirúrgicas sufridas'
    )

    documento = fields.Binary(
        string='Documento de consentimiento informado'
    )

    @api.model_create_multi
    def create(self, vals_list):
        for vals in vals_list:
            if vals.get('historia_clinica', 'Nuevo') == 'Nuevo':
                vals['historia_clinica'] = self.env['ir.sequence'].next_by_code(
                    'hospital.paciente.historia'
                ) or 'Nuevo'

        return super().create(vals_list)

    @api.depends('fecha_nacimiento')
    def _compute_edad(self):
        hoy = date.today()

        for rec in self:
            if rec.fecha_nacimiento:
                rec.edad = relativedelta(hoy, rec.fecha_nacimiento).years
            else:
                rec.edad = 0

