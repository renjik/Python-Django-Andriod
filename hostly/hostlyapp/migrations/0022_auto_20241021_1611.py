# Generated by Django 3.2.23 on 2024-10-21 10:41

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('hostlyapp', '0021_auto_20241021_1508'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='attendance',
            name='ROOMALLOCATION',
        ),
        migrations.AddField(
            model_name='attendance',
            name='STUDENT',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='hostlyapp.student'),
            preserve_default=False,
        ),
    ]
