# Generated by Django 3.2.23 on 2024-10-23 08:49

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('hostlyapp', '0025_auto_20241022_2037'),
    ]

    operations = [
        migrations.AddField(
            model_name='maintenance',
            name='description',
            field=models.CharField(default='100', max_length=500),
        ),
    ]