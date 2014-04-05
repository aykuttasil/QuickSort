package com.aka.quicksort;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	int[] intdizi;

	public void btn_siralaClick(View vi) {

		try {
			EditText edittext = (EditText) findViewById(R.id.editTextSayilar);
			String degerler = edittext.getText().toString();
			degerler.replace(",", ".");

			String[] dizidegerler = degerler.split("\n");
			intdizi = new int[dizidegerler.length];

			for (int i = 0; i < dizidegerler.length; i++) {
				int sayi = Integer.parseInt(dizidegerler[i].trim());
				intdizi[i] = sayi;

			}

			QuickSort(intdizi, 0, intdizi.length - 1);

			edittextYaz();
		} catch (Exception e) {
			getAlertDialog("Lütfen doðru giriþ yapýnýz.");
		}

	}

	private void edittextYaz() {
		EditText edittext = (EditText) findViewById(R.id.editTextSayilar);
		edittext.setText("");

		for (int i = 0; i < intdizi.length; i++) {

			edittext.append("  "+Integer.toString(intdizi[i]) + "\n");

		}
	}

	public static void QuickSort(int[] input, int left, int right) {
		if (left < right) {
			int q = Partition(input, left, right);
			QuickSort(input, left, q - 1);
			QuickSort(input, q + 1, right);
		}
	}

	private static int Partition(int[] input, int left, int right) {
		int pivot = input[right];
		int temp;
		int i = left;
		for (int j = left; j < right; j++) {
			if (input[j] <= pivot) {
				temp = input[j];
				input[j] = input[i];
				input[i] = temp;
				i++;
			}
		}
		input[right] = input[i];
		input[i] = pivot;
		return i;
	}

	private void getAlertDialog(String deger) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(getPackageName()).setPositiveButton("Tamam", null)
				.setMessage(deger);
		alert.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
