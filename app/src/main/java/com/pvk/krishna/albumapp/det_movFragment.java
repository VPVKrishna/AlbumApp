//package com.pvk.krishna.albumapp;
//
//import android.app.Fragment;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class det_movFragment extends Fragment {
//    public String revmovieJsonStr;
//    public ArrayList<String> rev_arr = new ArrayList<String>();
//
//    public det_movFragment() {
//    }
//
//    @Override
//    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootview = inflater.inflate(R.layout.fragment_det_mov, container, false);
//        Intent intent = getActivity().getIntent();
//        final Bundle extras = intent.getExtras();
//        if (intent != null) {
//            String put_ext_tit = extras.getString("title");
//            String put_ext_rel_date = extras.getString("rel_date");
//            String url_pos = extras.getString("url_pos");
//            int posit = extras.getInt("posit");
//            String over = extras.getString("over_ex");
//            final String id = extras.getString("id_ex");
//
//            if (put_ext_tit != null && id != null) {
//
//
//                ((TextView) rootview.findViewById(R.id.title_mov))
//                        .setText(put_ext_tit);
//                ((TextView) rootview.findViewById(R.id.rel_date_mov))
//                        .setText(put_ext_rel_date);
//                ImageView im2 = ((ImageView) rootview.findViewById(R.id.pos_im));
//
//                Picasso.with(getActivity()).load(url_pos)
//
//                        .centerCrop()
//                        .resize(400, 400)
//                        .into(im2);
//                ((TextView) rootview.findViewById(R.id.over))
//                        .setText(over);
//
//                Button b = (Button) rootview.findViewById(R.id.rev);
//                b.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        getrev task=new getrev(){
//                            @Override
//                            protected void onPostExecute(Void aVoid) {
//                                super.onPostExecute(aVoid);
//                                String[] revi_arr = rev_arr.toArray(new String[rev_arr.size()]);
//                                Log.e("mayank1", revi_arr[1]);
//                                Intent intent_rev = new Intent(getActivity(), det_mov_rev.class);
//                                Bundle ex_rev = new Bundle();
//                                ex_rev.putStringArrayList("rev_list", rev_arr);
//                                intent_rev.putExtras(ex_rev);
//                                startActivity(intent_rev);
//
//                            }
//                        };
//                        task.execute(id);
//
//                    }
//                });
//
//            } else {
//                ((TextView) rootview.findViewById(R.id.title_mov)).setText("mayank");
//            }
//        }
//
//
//        return rootview;
//    }
//
//    public class getrev extends AsyncTask<String, String, Void> {
//        private final String LOG_TAG = getrev.class.getSimpleName();
//        public int count;
//        String[] sin_rev;
//        String[] aut_rev;
//
//        @Override
//        protected Void doInBackground(String... params) {
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            try {
//                final String movie_rev_url_str = "http://api.themoviedb.org/3/movie/" + params[0] + "/reviews?api_key=8d7a48043ba1d3348181e2b6615cedc7";
//                Log.v("url", movie_rev_url_str);
//                URL movie_rev_url = new URL(movie_rev_url_str);
//                urlConnection = (HttpURLConnection) movie_rev_url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    return null;
//                }
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                }
//                if (buffer.length() == 0) {
//                    return null;
//                }
//                revmovieJsonStr = buffer.toString();
//
//                JSONObject revmovieJson = new JSONObject(revmovieJsonStr);
//                JSONArray revmovieJsonarray = revmovieJson.getJSONArray("results");
//                count = revmovieJsonarray.length();
//                aut_rev = new String[count];
//                sin_rev = new String[count];
//                for (int i = 0; i < count; i++) {
//                    JSONObject sin_rev_json = revmovieJsonarray.getJSONObject(i);
//                    sin_rev[i] = sin_rev_json.getString("content");
//                    Log.e("mayank", sin_rev[i]);
//
//                    publishProgress((String) sin_rev[i]);
//
//                }
//            } catch (IOException | JSONException e) {
//                Log.e(LOG_TAG, "Error ", e);
//                return null;
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e(LOG_TAG, "Error closing stream", e);
//                    }
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//
//            rev_arr.add(values[0]);
//
//        }
//    }
//}