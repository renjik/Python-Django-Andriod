# Generated by Django 3.2.23 on 2024-10-22 15:07

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('hostlyapp', '0024_auto_20241022_1951'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='checkincheckout',
            name='reason',
        ),
        migrations.AddField(
            model_name='checkincheckout',
            name='date_time',
            field=models.CharField(default=1, max_length=200),
            preserve_default=False,
        ),
    ]
