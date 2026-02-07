# -*- coding: utf-8 -*-

from datetime import date
from odoo import models, fields, api
from odoo.exceptions import ValidationError

class HospitalPersonal(models.Model):

    _name = 'hospital.personal'

    _description = 'Personal del Hospital'

    _order = 'nombre'

    _rec_name = 'nombre'

    # === CAMPOS ===

    nombre = fields.Many2one(
        'hr.employee',
        string='Nombre',
        required=True,
        ondelete='cascade'
    )
    
    fecha_alta = fields.Date(
        string='Fecha alta empleado',
        default=fields.Date.context_today,
        readonly=True
    )

    fecha_baja = fields.Date(
        string='Fecha baja empleado'
    )

    motivo_baja = fields.Selection([
        ('jubilacion', 'Jubilación'),
        ('despido', 'Despido'),
        ('dimision', 'Dimisión')
    ])

