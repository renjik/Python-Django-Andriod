# Generated by Django 4.1.7 on 2024-10-14 05:17

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('hostlyapp', '0007_alter_student_dob'),
    ]

    operations = [
        migrations.AlterField(
            model_name='student',
            name='parents_phone',
            field=models.CharField(max_length=200),
        ),
        migrations.AlterField(
            model_name='student',
            name='phone_number',
            field=models.CharField(max_length=200),
        ),
        migrations.AlterField(
            model_name='student',
            name='pin_no',
            field=models.CharField(max_length=200),
        ),
        migrations.AlterField(
            model_name='student',
            name='sem',
            field=models.CharField(max_length=200),
        ),
    ]
