# Generated by Django 4.1.7 on 2024-10-15 07:47

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('hostlyapp', '0013_payment'),
    ]

    operations = [
        migrations.RenameField(
            model_name='roomallocation',
            old_name='room',
            new_name='ROOM',
        ),
    ]
