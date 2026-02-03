from odoo import models, fields, api
from odoo.exceptions import ValidationError

class Event(models.Model): 
    _name = 'event.management'
    
    name = fields.Char(default="Nuevo evento")
    start_date = fields.Date(default=lambda self: fields.Date.today()) 
    attendees = fields.Many2many('res.partner', string="Asistentes") 
    seats = fields.Integer(default=50) 
    taken_seats = fields.Float(compute="_compute_taken", store=True) 
    
    @api.depends('seats', 'attendees') 
    def _compute_taken(self): 
        for r in self: 
            if not r.seats: 
                r.taken_seats = 0.0 
            else: 
                r.taken_seats = 100.0 * len(r.attendees) / r.seats
    
    @api.constrains('seats')
    def _check_seats(self):
        for r in self: 
            if r.seats < 0: 
                raise ValidationError("El número de asientos no puede ser negativo.")