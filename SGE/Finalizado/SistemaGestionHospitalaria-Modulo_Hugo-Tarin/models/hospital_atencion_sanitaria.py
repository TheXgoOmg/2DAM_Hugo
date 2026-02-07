# -*- coding: utf-8 -*-

from datetime import date
from dateutil.relativedelta import relativedelta
from odoo import models, fields, api
from odoo.exceptions import ValidationError

class HospitalAtencionSanitaria(models.Model):

    _name = 'hospital.atencion.sanitaria'

    _description = 'Atención Sanitaria del Hospital'

    _order = 'icu'

    _rec_name = 'icu'

    # === CAMPOS ===

    icu = fields.Char(
        string='Nº ICU',
        required=True,
        copy=False,
        readonly=True,
        default='Nuevo',
    )

    paciente_id = fields.Many2one('res.partner', string='Paciente', required=True)

    dni = fields.Char(
        string='DNI',
        related='paciente_id.vat',
        readonly=True
    )

    sip = fields.Char(
        string='SIP'
    )

    tipo_atencion = fields.Selection([
        ('aseguradora', "Compañía aseguradora"),
        ('privado', 'Privado'),
    ], string="Tipo de Atención", required=True)
    
    cuenta_bancaria = fields.Char(
        string="Nº Cuenta Bancaria"
    )

    aseguradora = fields.Char(
        string="Compañía aseguradora"
    )

    n_asegurado = fields.Char(
        string="Nº asegurado"
    )

    fecha_alta = fields.Datetime(
        string="Fecha alta",
        default=fields.Datetime.now,
        readonly=True,
    )

    responsable = fields.Many2one(
        'hr.employee',
        string="Profesional responsable",
        required=True,
    )

    otros_profesionales = fields.Many2many(
        'hr.employee',
        string="Otros profesionales",
    )

    anamnesis = fields.Char(
        string="Anamnesis"
    )

    tipo_prueba = fields.Selection([
        ('analitica', 'Analítica de Sangre'),
        ('radiologia', 'Radiología Convencional (RX)'),
        ('tomografia', 'Tomografía (TAC)'),
        ('resonancia', 'Resonancia Magnética (RMN)'),
        ('ecografia', 'Ecografía y Doppler'),
        ('orina', 'Pruebas de Orina y Cultivos'),
        ('electrocardiograma', 'Electrocardiograma (ECG)'),
        ('respiratorias', 'Pruebas Respiratorias'),
        ('endoscopia', 'Endoscopia / Invasiva'),
        ('otras', 'Otras Especialidades')
    ], string='Tipo de Prueba', required=True)

    documento_pruebas = fields.Binary(
        string="Documentos de pruebas médicas"
    )

    diagnostico = fields.Char(
        string="Diagnóstico"
    )

    tratamiento = fields.Char(
        string="Tratamiento"
    )

    datos_evolucion = fields.Char(
        string="Datos de evolución"
    )

    bool_hospitalizacion = fields.Boolean(
        string="Requiere hospitalización"
    )

    observaciones = fields.Char(
        string="Observaciones"
    )

    @api.model_create_multi
    def create(self, vals_list):
        for vals in vals_list:
            if vals.get('icu', 'Nuevo') == 'Nuevo':
                vals['icu'] = self.env['ir.sequence'].next_by_code(
                    'hospital.atencion.icu'
                ) or 'Nuevo'

        return super().create(vals_list)
